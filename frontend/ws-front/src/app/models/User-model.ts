import { ChatType } from "./Chat-Type-model.enum";
import { StatusUser } from "./Status-User.enum";

export class User {
    public identificador!: string;
    public nome!: string;
    public totalPontos!: number;
    public statusUser!: StatusUser;
    public chatType!: ChatType;
}

