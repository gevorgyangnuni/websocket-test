import {Component, OnDestroy, OnInit} from '@angular/core';
import {MessageService} from './service/message.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  viewProviders: [MessageService]
})

export class AppComponent implements OnInit, OnDestroy {

  public messages: Array<any>;

  constructor(private _messageService: MessageService) {
  }

  ngOnInit(): void {
    this._messageService.subscribe();
    this.messages = this._messageService.getMessages();
  }

  ngOnDestroy(): void {
    this._messageService.unsubscribe();
  }

  public sendMessage(message): void {
    this._messageService.send(message);
  }
}
