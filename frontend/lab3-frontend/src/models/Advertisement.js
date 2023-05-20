import City from "./City";
import BoardGame from "./BoardGame";
import User from "./User";

export default class Advertisement {
    constructor(data) {
        this.id = data.id;
        this.condition = data.condition;
        this.price = data.price;
        this.expiryDate = data.expiryDate;
        this.city = new City(data.city);
        this.boardGame = new BoardGame(data.boardGame);
        this.user = new User(data.user);
    }
}
