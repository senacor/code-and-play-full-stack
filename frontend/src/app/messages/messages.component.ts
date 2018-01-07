import { Component, OnInit } from '@angular/core';
import {ChatMessage} from "../shared/chat-message.model";

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

  messages: ChatMessage[] = [];

  constructor() { }

  ngOnInit() {
    this.fetchChatMessages().then(messages => this.messages = messages);
  }

  private fetchChatMessages(): Promise<ChatMessage[]> {
    return Promise.resolve(MESSAGES.slice(0)); // TODO: get message data from the server;
  }
}
