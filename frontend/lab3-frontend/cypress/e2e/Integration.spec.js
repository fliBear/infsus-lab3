describe("MasterComponent integration test", () => {
    it("Loading data", () => {
        cy.intercept("GET", "http://localhost:8080/boardGames").as(
            "boardGamesRequest"
        );
        cy.intercept("GET", "http://localhost:8080/advertisements").as(
            "advertisementsRequest"
        );

        cy.visit("http://127.0.0.1:4173/master-detail/1");

        cy.wait("@boardGamesRequest").should("have.property", "response");
        cy.wait("@advertisementsRequest").should("have.property", "response");
    });
});
