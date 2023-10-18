package com.example.redisproject.Cache.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;



public record UserProfile(String name, int age) {
}
