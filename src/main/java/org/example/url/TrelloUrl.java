package org.example.url;

public class TrelloUrl {

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARDS = "/boards";

    private TrelloUrl(){
    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBoardsUrl() {
        return getBaseUrl() + BOARDS;
    }

    public static String getBoardUrl(String boardId) {
        return  getBoardsUrl() + "/" + boardId;
    }

}
