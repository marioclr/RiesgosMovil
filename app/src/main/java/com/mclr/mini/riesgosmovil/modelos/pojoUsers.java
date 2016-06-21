package com.mclr.mini.riesgosmovil.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class pojoUsers implements Parcelable {
	String guid;
	String QrCode;
	String UserName;
	String Password;
	int Active;
	int Lock;
	int Deleted;
	String userID;
	
	public pojoUsers(){
		guid = "";
		QrCode = "";
		UserName = "";
		Password =  "";
		Active = 1;
		Lock = 0;
		Deleted = 0;
		userID = "";
	}
	
	private pojoUsers(Parcel in) {
		this.guid = in.readString();
		this.QrCode = in.readString();
		this.UserName = in.readString();
		this.Password = in.readString();
		this.Active = in.readInt();
		this.Lock = in.readInt();
		this.Deleted = in.readInt();
		this.userID = in.readString();
	}

	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getQrCode() {
		return QrCode;
	}
	public void setQrCode(String qrCode) {
		QrCode = qrCode;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getActive() {
		return Active;
	}
	public void setActive(int active) {
		Active = active;
	}
	public int getLock() {
		return Lock;
	}
	public void setLock(int lock) {
		Lock = lock;
	}
	public int getDeleted() {
		return Deleted;
	}
	public void setDeleted(int deleted) {
		Deleted = deleted;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(guid);
		dest.writeString(QrCode);
		dest.writeString(UserName);
		dest.writeString(Password);
		dest.writeInt(Active);
		dest.writeInt(Lock);
		dest.writeInt(Deleted);
		dest.writeString(userID);
	}

	public static final Creator<pojoUsers> CREATOR = new Creator<pojoUsers>() {
		public pojoUsers createFromParcel(Parcel in) {
			return new pojoUsers(in);
		}
		public pojoUsers[] newArray(int size) {
			return new pojoUsers[size];
		}

	};

}
