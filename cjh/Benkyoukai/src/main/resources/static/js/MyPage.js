function deleteAccount(id) {
    let check = confirm("Are you sure you want to delete your account?");
    const id1 = document.getElementById("id1").value;
    if (check) {
        try {
            console.log(check)
            console.log(id1)
            const response = fetch('/delete?id=' + id1, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' }
            });

            // 비동기적으로 결과를 처리
            response.then(result => result.text())  // result.text()는 비동기적으로 실행
                .then(resultText => {
                    console.log(resultText);  // 서버로부터 받은 응답 출력

                    // 응답 결과에 따라 분기
                    if (resultText === "deleted") {
                        alert("Your account has been successfully deleted.");
                    } else {
                        alert("Error occurred; try again later.");
                    }

                    window.location.href = '/';  // 완료 후 리디렉션
                })
                .catch(error => {
                    alert("Error occurred: " + error.message);
                });
        } catch (error) {
            alert("Error occurred: " + error.message);
        }
    }
}
document.getElementById('uploadForm').addEventListener('submit', function(e) {
    e.preventDefault();  // 기본 폼 제출 방지

    const formData = new FormData(this);  // 폼 데이터 가져오기
    const xhr = new XMLHttpRequest();  // AJAX 요청 객체 생성

    xhr.open('POST', '/upload', true);

    xhr.onload = function() {
        if (xhr.status === 200) {
            // 업로드가 성공적으로 완료된 경우, 이미지를 새로고침
            document.getElementById('uploadedImage').src = "/images/profile.png" // + new Date().getTime();  // 캐시 방지
            location.href = '/';
        } else {
            alert("Error uploading the image.");
        }
    };

    xhr.send(formData);  // 폼 데이터 전송
});
