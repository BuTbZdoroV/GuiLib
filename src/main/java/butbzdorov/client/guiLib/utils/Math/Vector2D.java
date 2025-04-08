package butbzdorov.client.guiLib.utils.Math;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Vector2D {
    // Сеттеры
    // Геттеры
    private double x, y;

    // Конструктор
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Сложение векторов
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    // Вычитание векторов
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    // Умножение вектора на скаляр
    public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    // Длина (модуль) вектора
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    // Нормализация (приведение к единичной длине)
    public Vector2D normalize() {
        double length = length();
        return length == 0 ? new Vector2D(0, 0) : new Vector2D(x / length, y / length);
    }

    // Скалярное произведение
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    // Поворот на угол (в радианах)
    public Vector2D rotate(double angleRad) {
        double cos = Math.cos(angleRad);
        double sin = Math.sin(angleRad);
        return new Vector2D(x * cos - y * sin, x * sin + y * cos);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}