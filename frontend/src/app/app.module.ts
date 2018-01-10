import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";


import { AppComponent } from './app.component';
import { MessagesComponent } from './messages/messages.component';
import { ChannelSelectorComponent } from './channel-selector/channel-selector.component';
import { MessageFormComponent } from './message-form/message-form.component';


@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    ChannelSelectorComponent,
    MessageFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
