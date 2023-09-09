import { ChatType } from "./Chat-Type-model.enum";
import {MessageType} from "./MessageType-model.enum";

export class Question {
    public correctOption!: number;
    public customMessage!: string;
    public options!: string[];
    public questionText!: string;
    public messageType!: MessageType;
    public chatType!: ChatType;

}