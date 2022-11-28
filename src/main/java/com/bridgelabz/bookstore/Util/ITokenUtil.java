package com.bridgelabz.bookstore.Util;

public interface ITokenUtil {
    String createToken(int id);
    int decodeToken(String token);
}
