import City from "./City";
import BoardGame from "./BoardGame";

export default class Advertisement {
    constructor(data) {
        this.id = data.id;
        this.condition = data.condition;
        this.city = new City(data.city);
        this.boardGame = new BoardGame(data.boardGame);
    }
}
