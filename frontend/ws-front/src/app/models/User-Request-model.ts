import { StatusUser } from "./Status-User.enum";

export class UserRequest {

    public identificador!: string;
    public letra!: string;
    public totalPontos!: number;
    public questionIndex!: number;
    public status!: StatusUser;
}