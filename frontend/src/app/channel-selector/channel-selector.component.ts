import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Channel} from "../shared/channel.model";

const CHANNELS : Channel[] = [
  new Channel("general"),
  new Channel("dev"),
  new Channel("humor")
];

@Component({
  selector: 'app-channel-selector',
  templateUrl: './channel-selector.component.html',
  styleUrls: ['./channel-selector.component.css']
})
export class ChannelSelectorComponent implements OnInit {

  currentChannel: string;
  channels: Channel[] = [];

  @Output() onChannelSelected = new EventEmitter<Channel>();

  constructor() { }

  ngOnInit() {
    this.fetchChannels().then(channels => {
      this.channels = channels;
      this.initCurrentChannel();
    });
  }

  public onChannelChanged() {
    this.onChannelSelected.emit(new Channel(this.currentChannel));
  }

  private fetchChannels(): Promise<Channel[]> {
    return Promise.resolve(CHANNELS.slice(0)); // TODO: get channels from the server;
  }

  private initCurrentChannel() {
    if (this.currentChannel === undefined && this.channels.length > 0) {
      this.currentChannel = this.channels[0].name;
      this.onChannelChanged();
    }
  }

}
