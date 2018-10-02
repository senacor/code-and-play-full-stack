import {Component, Input, OnInit} from '@angular/core';
import {ChatMessage} from "../shared/chat-message.model";
import {Channel} from "../shared/channel.model";
import {MessagesService} from "../services/messages.service";

@Component({
  selector: 'app-messages',
  providers: [ MessagesService ],
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  _currentChannel: Channel;

  messages: ChatMessage[] = [];

  constructor(private messagesService: MessagesService) { }

  ngOnInit() { }

  @Input()
  set currentChannel(currentChannel: Channel) {
    this._currentChannel = currentChannel;
    // load messages for the current channel
    this.messages = [];
    this.messagesService.fetchMessages(this._currentChannel).then(messages => this.messages = messages);
  }

  handlePostedMessage(message: ChatMessage) {
    this.messages.unshift(message);
  }

}
