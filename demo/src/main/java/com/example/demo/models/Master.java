package com.example.demo.models;

public class Master
{
    private int Id;
    private String surname;
    private  String name;
    private String patronymic;
    private String date;
    private String gender;
    private String telephone;
    private String grade;
    private String experience;
    private String post;
    private String education;
    private String email;

    public Master() {
    }

    public Master(int id, String surname, String name, String patronymic, String date,
                  String gender, String telephone, String grade, String experience,
                  String post, String education, String email)
    {
        Id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.date = date;
        this.gender = gender;
        this.telephone = telephone;
        this.grade = grade;
        this.experience = experience;
        this.post = post;
        this.education = education;
        this.email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Master{" +
                "Id=" + Id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", date='" + date + '\'' +
                ", gender='" + gender + '\'' +
                ", telephone='" + telephone + '\'' +
                ", grade='" + grade + '\'' +
                ", experience='" + experience + '\'' +
                ", post='" + post + '\'' +
                ", education='" + education + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
