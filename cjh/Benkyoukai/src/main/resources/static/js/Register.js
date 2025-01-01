function registerAccount(id, pw, name, age) {
    const id1 = document.getElementById("id").value;
    const pw1 = document.getElementById("pw").value;
    const name1 = document.getElementById("name").value;
    const age1 = document.getElementById("age").value;

    try {
        // GET 요청을 할 때는 URL에 쿼리 문자열로 데이터를 추가합니다.
        const response = fetch('/registery?id=' + encodeURIComponent(id1) + '&pw=' + encodeURIComponent(pw1) +
            '&name=' + encodeURIComponent(name1) + '&age=' + encodeURIComponent(age1), {
            method: 'GET' // 'GET' 요청은 body가 필요 없습니다.
        });

        // 응답이 오면 처리
        response.then(result => result.text())
            .then(resultText => {
                if (resultText === "same") {
                    alert("Same ID is existing, try another one.")

                } else {
                    alert("Your account has been successfully created.");
                    window.location.href= '/';
                }

            });
    } catch (error) {
        alert("Error occurred: " + error.message);
    }
}
