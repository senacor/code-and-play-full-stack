import { Component } from '@angular/core';
import {User} from "./shared/user.model";
import {Channel} from "./shared/channel.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  user: User = new User('Chatter', 'sender@test.de');
  currentChannel: Channel = null;

  onChannelSelected(channel: Channel) {
    this.currentChannel = channel;
  }
}
