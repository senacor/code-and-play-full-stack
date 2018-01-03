import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Channel} from "../shared/channel.model";
import 'rxjs/add/operator/map'
import {ChatMessage} from "../shared/chat-message.model";

@Injectable()
export class MessagesService {

  constructor(private http: HttpClient) {
  }

  fetchMessages(channel: Channel): Promise<ChatMessage[]> {
    return this.http.get(`/api/v1/channels/${channel.id}/messages`).map(data => data as ChatMessage[]).toPromise();
  }

  sendMessage(channel: Channel, message: ChatMessage): void {
    this.http.post(`/api/v1/channels/${channel.id}/messages`, message).toPromise();
  }

}
