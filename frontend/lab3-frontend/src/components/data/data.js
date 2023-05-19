let example = {
    id: 1,
    price: 7,
    condition: "Dobro",
    expiryDate: "2023-05-19T13:52:36.933+00:00",
    advertisementDate: "2023-05-19T13:52:36.933+00:00",
    user: {
        id: 1,
        userName: "korisnik",
        email: "email@email.com",
        phoneNumber: "0987654321",
        age: 20,
        role: { id: 1, description: "Admin" },
        city: {
            id: 1,
            name: "Slavonski Brod",
            county: { id: 1, name: "Brodsko-posavska županija" },
        },
    },
    boardGame: {
        id: 1,
        name: "Uno Board",
        noPlayersMin: 2,
        noPlayersMax: 10,
        age: 10,
        avgPlayingTime: 3,
        publisher: { id: 1, name: "Disney" },
        language: { id: 1, name: "Hrvatski" },
        genre: {
            id: 1,
            name: "Kartaška igra",
            description: "Igra se s kratama",
        },
    },
    city: {
        id: 1,
        name: "Slavonski Brod",
        county: { id: 1, name: "Brodsko-posavska županija" },
    },
};
export default example;
