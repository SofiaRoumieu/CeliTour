package com.example.celitour.FormularioRestaurante;

import java.io.Serializable;
import java.util.Dictionary;

public class RestauranteModel implements Serializable {
    private Integer id;
    private String nombre;
    private String calle;
    private String altura;
    private String entre1;
    private String entre2;
    private String provincia;
    private String localidad;
    private String telefono;
    private String email;
    private String instagram;
    private String facebook;
    private String twitter;
    private String menu;
    private Double latitud;
    private Double longitud;
    private Boolean activo;
    private String web;
    private String horario;

    //constructores
    public RestauranteModel(){}
    public RestauranteModel(Integer id,String nombre,String calle, String altura, String entre1,
    String entre2, String provincia, String localidad, String telefono, String email, String instagram, String facebook, String twitter,
                            String menu, Double latitud, Double longitud, Boolean activo, String web, String horario){
        this.id=id;
        this.nombre=nombre;
        this.calle=calle;
        this.altura=altura;
        this.entre1=entre1;
        this.entre2=entre2;
        this.provincia=provincia;
        this.localidad=localidad;
        this.telefono=telefono;
        this.email=email;
        this.instagram=instagram;
        this.facebook=facebook;
        this.twitter=twitter;
        this.menu=menu;
        this.latitud=latitud;
        this.longitud=longitud;
        this.activo=activo;
        this.web=web;
        this.horario=horario;

    }

    //getters setters
    public String getNombre (){ return nombre; }
    public void setNombre (String nombre){ this.nombre = nombre;}
    public Boolean getActivo() {return activo;}
    public void setActivo(Boolean activo) {this.activo = activo;}
    public String getAltura() {return altura;}
    public void setAltura(String altura) {this.altura = altura;}
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}public String getCalle() {return calle;}
    public void setCalle(String calle) {this.calle = calle;}
    public String getEntre1() {return entre1;}
    public void setEntre1(String entre1) {this.entre1 = entre1;}
    public String getEntre2() {return entre2;}
    public void setEntre2(String entre2) {this.entre2 = entre2;}
    public String getProvincia() {return provincia;}
    public void setProvincia(String provincia) {this.provincia = provincia;}
    public String getLocalidad() {return localidad;}
    public void setLocalidad(String localidad) {this.localidad = localidad;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getInstagram() {return instagram;}
    public void setInstagram(String instagram) {this.instagram = instagram;}
    public String getFacebook() {return facebook;}
    public void setFacebook(String facebook) {this.facebook = facebook;}
    public String getTwitter() {return twitter;}
    public void setTwitter(String twitter) {this.twitter = twitter;}
    public String getMenu() {return menu;}
    public void setMenu(String menu) {this.menu = menu;}
    public Double getLatitud() {return latitud;}
    public void setLatitud(Double latitud) {this.latitud = latitud;}
    public Double getLongitud() {return longitud;}
    public void setLongitud(Double longitud) {this.longitud = longitud;}
    public String getWeb() {return web;}
    public void setWeb(String web) {this.web = web;}
    public String getHorario() {return horario;}
    public void setHorario(String horario) {this.horario = horario;}

    @Override
    public String toString(){
        return "{" +
                "id='" + id + "'"+
                ",nombre='" + nombre + "'"+
                ",calle='" + calle + "'"+
                ",altura='" + altura + "'"+
                ",entre1='" + entre1 + "'"+
                ",entre2='" + entre2 + "'"+
                ",provincia='" + provincia + "'"+
                ",localidad='" + localidad + "'"+
                ",telefono='" + telefono + "'"+
                ",instagram='" + instagram + "'"+
                ",facebook='" + facebook + "'"+
                ",twitter='" + twitter + "'"+
                ",menu='" + menu + "'"+
                ",latitud='" + latitud + "'"+
                ",longitud='" + longitud + "'"+
                ",activo='" + activo + "'"+
                ",web='" + web + "'"+
                ",horario='" + horario + "'"+
                "}";
    }
}
