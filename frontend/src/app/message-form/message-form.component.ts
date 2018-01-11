import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Channel} from "../shared/channel.model";
import {ChatMessage} from "../shared/chat-message.model";
import {MessagesService} from "../services/messages.service";

@Component({
  selector: 'app-message-form',
  providers: [ MessagesService ],
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  message: string;

  @Input() channel: Channel;

  @Output() onMessagePosted = new EventEmitter<ChatMessage>();

  constructor(private messagesService: MessagesService) { }

  ngOnInit() { }

  onSubmit() {
    // TODO: get the logged in user as sender
    let sender = "sender@test.de";
    let newChatMessage = new ChatMessage(this.message, sender, new Date());
    this.messagesService.sendMessage(this.channel, newChatMessage);
    this.onMessagePosted.emit(newChatMessage);
    this.message = "";
  }
}

