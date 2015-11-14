package com.sapibagus.android.api.model.entity;

public final class AuthorEntity {
    public final long id;
    public final String slug;
    public final String name;
    public final String firstName;
    public final String lastName;
    public final String nickname;
    public final String url;
    public final String description;

    public AuthorEntity(long id, String slug, String name, String firstName, String lastName, String nickname, String url, String description) {
        this.id = id;
        this.slug = slug;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.url = url;
        this.description = description;
    }
}
