package com.example.cachedemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<App,Integer> {

    public App findAppByName(String name);

}
