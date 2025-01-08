const goSignUp = () => {
    location.href = "/sign/goSignUp";
}
const doSign = () => {
    const id = document.getElementById("input-id").value;
    const pw = document.getElementById("input-pw").value;

    axios.post("/sign/doSign", {
        id,
        pw
    }).then(res => {
        console.log(res);
        if(res.status === 200) {
            location.href = "/main/";
        } else {
            alert("유저정보를 다시 확인하세요.");
            location.href = "/"
        }
    }).catch(err => {
        console.error(err);
    })
}