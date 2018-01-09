import {Component, Input, OnInit} from '@angular/core';
import {ChatMessage} from "../shared/chat-message.model";
import {Channel} from "../shared/channel.model";

const MESSAGES : ChatMessage[] = [
  new ChatMessage("Hello World!", "sender@test.de", new Date()),
  new ChatMessage("This is another message.", "sender@test.de", new Date())
];

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  _currentChannel: Channel;

  messages: ChatMessage[] = [];

  constructor() { }

  ngOnInit() { }

  @Input()
  set currentChannel(currentChannel: Channel) {
    this._currentChannel = currentChannel;
    // load messages for the current channel
    this.messages = [];
    this.fetchChatMessages().then(messages => this.messages = messages);
  }

  private fetchChatMessages(): Promise<ChatMessage[]> {
    return Promise.resolve(MESSAGES.slice(0)); // TODO: get message data from the server;
  }
}
