package com.company;

    public class Score {
        private final String name;
        private int score;

        public Score(String name, int score){
            this.name = name;
            this.score = score;
        }
        Score(){
            this("", 0);
        }


        public int getScore() {
            return score;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return name + " " + score;
        }

        public void addScore(int score) {
            this.score += score;
        }
    }
