import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Channel} from "../shared/channel.model";
import 'rxjs/add/operator/map'

@Injectable()
export class ChannelsService {

  constructor(private http: HttpClient) { }

  fetchChannels(): Promise<Channel[]> {
    return this.http.get('/api/v1/channels').map(data => data as Channel[]).toPromise();
  }

}
