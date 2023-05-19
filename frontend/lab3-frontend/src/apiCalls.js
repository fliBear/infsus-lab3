const base = "http://localhost:8080/";
let calls = {
    userAccount: base + "user/",
    allUserAccounts: base + "users",
    createUser: base + "createUserAccount",
    deleteUser: base + "deleteUserAccount/",
    editUser: base + "editUserAccount/",
    boardGame: base + "boardGame/",
    allBoardGames: base + "boardGames",
    createBoardGame: base + "createBoardGame",
    deleteBoardGame: base + "deleteBoardGame/",
    editBoardGame: base + "editBoardGame/",
    genres: base + "genres",
    languages: base + "languages",
    publishers: base + "publishers",
    advertisement: base + "advertisement/",
    allAdvertisements: base + "advertisements",
    createAdvertisement: base + "createAdvertisement",
    deleteAdvertisement: base + "deleteAdvertisement/",
    editAdvertisement: base + "editAdvertisement/",
    allPublishers: base + "publishers",
};

export default calls;
