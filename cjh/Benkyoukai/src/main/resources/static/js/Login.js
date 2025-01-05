async function tryLogin() {
    const id = document.getElementById('id').value;
    const pw = document.getElementById('pw').value;

    if (!id || !pw) {
        alert("Please fill in both fields.");
        return;
    }

    try {
        const response = await fetch('/tryLogin?id=' + id + '&pw=' + pw, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },

        });

        if (response.ok) {
            const result = await response.text();
            if (result==="approved") {
                alert("Login successful!");
                window.location.href = '/'; // 성공 후 리다이렉트
            } else {
                alert("Login failed: " + result.message);
            }
        } else {
            alert("Server error. Please try again later.");
        }
    } catch (error) {
        alert("Error: " + error.message);
    }
}