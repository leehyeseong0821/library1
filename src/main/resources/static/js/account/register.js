
window.onload = () => {
    RegisterEvent.getInstance().addRegisterSubmitOnclickEvent();
}

class RegisterApi {
    static #instance = null;
    static getInstance() {
        if(this.#instance == null) {
            this.#instance = new RegisterApi();
        }
        return this.#instance;
    }

    register(user) {

        $.ajax({
            async: false,
            type: "post",
            url: "/api/account/register",
            contentType: "application/json",
            data: JSON.stringify(user),
            dataType: "json",
            success: response => {
                console.log(response);
            },
            error: error => {
                console.log(error);                                //아래 ()내용은 에러객체
                RegisterService.getInstance().setErrorMessage(error.responseJSON.data);
            }
        });
    }
}

    class RegisterService {
        static #instance = null;
        static getInstance() {
            if(this.#instance == null) {
                this.#instance = new RegisterService();
            }
            return this.#instance;
        }
        //메소드   
        setErrorMessage(errors){
            const registerError = document.querySelectorAll(".register-error");
            // 같은 객체안에서 호출하기때문에 this붙여준다.
            this.#clearErrorMessage();

            Object.keys(errors).forEach(error => {
                if(error == "username"){
                    registerError[0].textContent = errors[error];
                }else if(error == "password"){
                    registerError[1].textContent = errors[error];
                }else if(error == "repassword"){
                    registerError[2].textContent = errors[error];
                }else if(error == "name"){
                    registerError[3].textContent = errors[error];
                }else if(error == "email"){
                    registerError[4].textContent = errors[error];
                }
            });
        }
        //에러 객체들을 전부 반복시켜서 text Content를 비워라 
        //#을 붙이면 private 외부에서 쓸 일이 없음.
        #clearErrorMessage(){
        const registerError = document.querySelectorAll(".register-error");
        registerError.forEach(error=>{
            error.textContent="";
        });
        }
    }



    class RegisterEvent {
        static #instance = null;
        static getInstance() {
            if(this.#instance == null) {
                this.#instance = new RegisterEvent();
            }
            return this.#instance;
        }
    
    

        addRegisterSubmitOnclickEvent() {
            const registerSubmit = document.querySelector(".register-submit");
    
            registerSubmit.onclick = () => {
                const usernameValue = document.querySelectorAll(".register-inputs")[0].value;
                const passwordValue = document.querySelectorAll(".register-inputs")[1].value;
                const repasswordValue = document.querySelectorAll(".register-inputs")[1].value;
                const nameValue = document.querySelectorAll(".register-inputs")[3].value;
                const emailValue = document.querySelectorAll(".register-inputs")[4].value;

                const user = new User(usernameValue, passwordValue,repasswordValue, nameValue, emailValue);

                RegisterApi.getInstance().register(user);
        }
    }
}
    
class User {
    username = null;
    password = null;
    repassword =null;
    name = null;
    email = null;

    constructor(username, password,repassword,name, email) {
        this.username = username;
        this.password = password;
        this.repassword =repassword;
        this.name = name;
        this.email = email;
    }
}