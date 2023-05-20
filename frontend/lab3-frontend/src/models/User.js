import axios from "axios";
import City from "./City";
import BoardGame from "./BoardGame";
import Publisher from "./Publisher";
import Language from "./Language";
import Genre from "./Genre";
import Advertisement from "./Advertisement";
import Role from "./Role";
import calls from "../apiCalls";

export default class User {
    constructor(data) {
        this.id = data.id;
        this.username = data.userName;
        this.email = data.email;
        this.phoneNumber = data.phoneNumber;
        this.age = data.age;
        this.role = new Role(data.role);
        this.city = new City(data.city);
    }

    static createBoardGame() {}

    static editBoardGame(
        id_v,
        name_v,
        noPlayersMax_v,
        noPlayersMin_v,
        age_v,
        avgPlayingTime_v,
        publisher_v,
        language_v,
        genre_v
    ) {
        let obj = {
            name: name_v,
            noPlayersMax: noPlayersMax_v,
            noPlayersMin: noPlayersMin_v,
            age: age_v,
            avgPlayingTime: avgPlayingTime_v,
            publisher: publisher_v,
            language: language_v,
            genre: genre_v,
        };
        axios.put(calls.editBoardGame + id_v, obj);
    }

    static createAd(
        price_v,
        condition_v,
        expiryDate_v,
        advertisementDate_v,
        user_id,
        boardgame_id,
        city_id
    ) {
        let obj = {
            price: price_v,
            condition: condition_v,
            expiryDate: expiryDate_v,
            advertisementDate: advertisementDate_v,
            user: user_id,
            boardgame: boardgame_id,
            city: city_id,
        };
        axios.post(calls.createAdvertisement, obj);
    }

    static editAd(
        id_v,
        price_v,
        condition_v,
        expiryDate_v,
        advertisementDate_v,
        user_id,
        boardgame_id,
        city_id
    ) {
        let obj = {
            price: price_v,
            condition: condition_v,
            expiryDate: expiryDate_v,
            advertisementDate: advertisementDate_v,
            user: user_id,
            boardgame: boardgame_id,
            city: city_id,
        };
        axios.put(calls.editAdvertisement + id_v, obj);
    }

    static removeAd(id) {
        axios.delete(calls.deleteAdvertisement + id);
    }

    static createUser(
        username_v,
        email_v,
        phoneNumber_v,
        age_v,
        role_v,
        city_v
    ) {
        let obj = {
            userName: username_v,
            email: email_v,
            phoneNumber: phoneNumber_v,
            age: age_v,
            role: { id: role_v.id, description: role_v.description },
            city: { id: city_v.id, name: city_v.name },
        };
        axios.post(calls.createUser, obj);
    }

    static editUser(
        id_v,
        username_v,
        email_v,
        phoneNumber_v,
        age_v,
        role_v,
        city_v
    ) {
        let obj = {
            userName: username_v,
            email: email_v,
            phoneNumber: phoneNumber_v,
            age: age_v,
            role: { id: role_v.id, description: role_v.description },
            city: { id: city_v.id, name: city_v.name },
        };
        axios.put(calls.editUser + id_v, obj);
    }

    static async deleteUser(id) {
        axios.delete(calls.deleteUser + id);
    }

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
            console.log("Failed to load advertisements");
        }
    }

    static async loadUsers() {
        try {
            let response = await axios.get(calls.allUserAccounts);
            let data = response.data;
            return data.map((el) => {
                return new User(el);
            });
        } catch (error) {
            console.log("Failed to load users");
        }
    }

    static async loadRoles() {
        try {
            let response = await axios.get(calls.allRoles);
            let data = response.data;
            return data.map((el) => {
                return new Role(el);
            });
        } catch (error) {
            console.log("Failed to load roles");
        }
    }

    static async loadCities() {
        try {
            let response = await axios.get(calls.allCities);
            let data = response.data;
            return data.map((el) => {
                return new City(el);
            });
        } catch (error) {
            console.log("Failed to load cities");
        }
    }
}
