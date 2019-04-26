package com.headspire.theaterearlogin.activities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Model{
  @SerializedName("image")
  @Expose
  private String image;
  @SerializedName("price")
  @Expose
  private String price;
  @SerializedName("product_id")
  @Expose
  private Integer product_id;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("title")
  @Expose
  private String title;
  public Model(){
  }
  public Model(String image,String price,Integer product_id,String description,String title){
   this.image=image;
   this.price=price;
   this.product_id=product_id;
   this.description=description;
   this.title=title;
  }
  public void setImage(String image){
   this.image=image;
  }
  public String getImage(){
   return image;
  }
  public void setPrice(String price){
   this.price=price;
  }
  public String getPrice(){
   return price;
  }
  public void setProduct_id(Integer product_id){
   this.product_id=product_id;
  }
  public Integer getProduct_id(){
   return product_id;
  }
  public void setDescription(String description){
   this.description=description;
  }
  public String getDescription(){
   return description;
  }
  public void setTitle(String title){
   this.title=title;
  }
  public String getTitle(){
   return title;
  }
}