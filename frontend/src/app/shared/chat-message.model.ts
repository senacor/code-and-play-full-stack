export class ChatMessage {
  message: string;
  sender: string;
  creationTimestamp: Date;

  constructor(message: string, sender: string, creationTimestamp: Date) {
    this.message = message;
    this.sender = sender;
    this.creationTimestamp = creationTimestamp;
  }
}
