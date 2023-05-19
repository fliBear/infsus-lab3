import axios from "axios";
import City from "./City";
import BoardGame from "./BoardGame";
import Publisher from "./Publisher";
import Language from "./Language";
import Genre from "./Genre";
import Advertisement from "./Advertisement";
import calls from "../../apiCalls";

export default class User {
    static createGame() {}

    static editGame() {}

    static createAd() {}

    static editAd() {}

    static removeAd() {}

    static createUser() {}

    static editUser() {}

    static async loadBoardGame(id) {
        try {
            let response = await axios.get(calls.boardGame + id);
            let data = response.data;
            return new BoardGame(data);
        } catch (error) {
            console.log("Failed to load board game");
        }
    }

    static async loadLanguages() {
        try {
            let response = await axios.get(calls.languages);
            let data = response.data;
            return data.map((el) => {
                return new Language(el);
            });
        } catch (error) {
            console.log("Failed to load languages");
        }
    }

    static async loadGenres() {
        try {
            let response = await axios.get(calls.genres);
            let data = response.data;
            return data.map((el) => {
                return new Genre(el);
            });
        } catch (error) {
            console.log("Failed to load genres");
        }
    }

    static async loadPublishers() {
        try {
            let response = await axios.get(calls.publishers);
            let data = response.data;
            return data.map((el) => {
                return new Publisher(el);
            });
        } catch (error) {
            console.log("Failed to load publishers");
        }
    }

    static async loadAdvertisements() {
        try {
            let response = await axios.get(calls.allAdvertisements);
            let data = response.data;
            return data.map((el) => {
                return new Advertisement(el);
            });
        } catch (error) {
            console.log("Failed to load publishers");
        }
    }
}
