import Publisher from "./Publisher";
import Language from "./Language";
import Genre from "./Genre";

export default class BoardGame {
    constructor(data) {
        this.id = data.id;
        this.name = data.name;
        this.noPlayersMin = data.noPlayersMin;
        this.noPlayersMax = data.noPlayersMax;
        this.avgPlayingTime = data.avgPlayingTime;
        this.age = data.age;
        this.publisher = new Publisher(data.publisher);
        this.language = new Language(data.language);
        this.genre = new Genre(data.genre);
    }
}
