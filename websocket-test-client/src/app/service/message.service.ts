import {Injectable} from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import {Message} from '@stomp/stompjs';
import {WebSocketConfig} from '../config/websocket.config';
import {StompService, StompState} from '@stomp/ng2-stompjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  public state: Observable<string>;
  private subscription: Subscription;
  private rawMessages: Observable<Message>;
  private messages: Array<any> = [];
  private subscribed: boolean;

  constructor(private _stompService: StompService) {
  }

  public subscribe() {
    if (this.subscribed) {
      return;
    }
    this.state = this._stompService.state.pipe(map((state: number) => StompState[state]));
    this.rawMessages = this._stompService.subscribe(WebSocketConfig.loadMessagesEndpoint);
    this.subscription = this.rawMessages.subscribe(this.onNext);
    this.subscribed = true;
  }

  public unsubscribe() {
    if (!this.subscribed) {
      return;
    }
    this.subscription.unsubscribe();
    this.subscription = null;
    this.messages = null;
    this.subscribed = false;
  }

  private onNext = (message: Message) => {
    this.messages.splice(0);
    this.messages.push.apply(this.messages, (JSON.parse(message.body)));
  }

  public send(message) {
    this._stompService.publish(WebSocketConfig.sendMessagesEndpoint, `{"value": "${message}"}`);
  }

  public getMessages(): Array<any> {
    return this.messages;
  }
}

