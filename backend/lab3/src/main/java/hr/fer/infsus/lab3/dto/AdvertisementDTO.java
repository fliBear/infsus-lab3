package hr.fer.infsus.lab3.dto;

import hr.fer.infsus.lab3.models.BoardGame;
import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.models.UserAccount;

import java.util.Date;

    public class AdvertisementDTO {
        private Long id;
        private int price;
        private String condition;
        private Date expiryDate;
        private Date advertisementDate;

        private Long userId;
        private Long boardGameId;
        private Long cityId;

        public AdvertisementDTO() {
        }

        public AdvertisementDTO(Long id, int price, String condition, Date expiryDate, Date advertisementDate,
                                Long userId, Long boardGameId, Long cityId) {
            this.id = id;
            this.price = price;
            this.condition = condition;
            this.expiryDate = expiryDate;
            this.advertisementDate = advertisementDate;
            this.userId = userId;
            this.boardGameId = boardGameId;
            this.cityId = cityId;
        }

        // Getters and setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(Date expiryDate) {
            this.expiryDate = expiryDate;
        }

        public Date getAdvertisementDate() {
            return advertisementDate;
        }

        public void setAdvertisementDate(Date advertisementDate) {
            this.advertisementDate = advertisementDate;
        }
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getBoardGameId() {
            return boardGameId;
        }

        public void setBoardGameId(Long boardGameId) {
            this.boardGameId = boardGameId;
        }

        public Long getCityId() {
            return cityId;
        }

        public void setCityId(Long cityId) {
            this.cityId = cityId;
        }

    }

