package com.example.myapplication;

public class Third {

    private Second items;
    private Second graphql;

    public Third(Second items, Second graphql) {
        this.items = items;
        this.graphql = graphql;
    }

    public Second getItems() {
        return items;
    }

    public void setItems(Second items) {
        this.items = items;
    }

    public Second getGraphql() {
        return graphql;
    }

    public void setGraphql(Second graphql) {
        this.graphql = graphql;
    }
}



