import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Channel} from "../shared/channel.model";
import {ChannelsService} from "../services/channels.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-channel-selector',
  providers: [ ChannelsService ],
  templateUrl: './channel-selector.component.html',
  styleUrls: ['./channel-selector.component.css']
})
export class ChannelSelectorComponent implements OnInit {

  currentChannel: Channel;
  channels: Channel[] = [];

  @Output() onChannelSelected = new EventEmitter<Channel>();

  constructor(private channelsService: ChannelsService) { }

  ngOnInit() {
    this.channelsService.fetchChannels().then(channels => {
      this.channels = channels;
      this.initCurrentChannel();
    },
      e => console.error(e));
  }

  onChannelChanged() {
    this.onChannelSelected.emit(this.currentChannel);
  }

  private initCurrentChannel() {
    if (this.currentChannel === undefined && this.channels.length > 0) {
      this.currentChannel = this.channels[0];
      this.onChannelChanged();
    }
  }

}
