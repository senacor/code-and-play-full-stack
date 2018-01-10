import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Channel} from "../shared/channel.model";
import {ChatMessage} from "../shared/chat-message.model";

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  message: string;

  @Input() channel: Channel;

  @Output() onMessagePosted = new EventEmitter<ChatMessage>();

  constructor() { }

  ngOnInit() { }

  onSubmit() {
    // TODO: get the logged in user as sender
    let sender = "sender@test.de";
    // TODO: do a service call to post the new message to the current channel
    this.onMessagePosted.emit(new ChatMessage(this.message, sender, new Date()));
    this.message = "";
  }
}

