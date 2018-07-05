import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {MessageService} from './service/message.service';
import {StompConfig, StompService} from '@stomp/ng2-stompjs';
import {stompConfig} from './config/stomp.config';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [StompService, {provide: StompConfig, useValue: stompConfig}, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
