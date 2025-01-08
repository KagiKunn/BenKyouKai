let idValid = 0;

function idCheck(id){
    console.log(id);
    fetch('idCheck', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: id,
        }),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);

            if(data.message == 'true'){
                idValid=1;
                document.getElementById('text').innerText = 'this id is not taken';
            }
            else{
                idValid=0;
                document.getElementById('text').innerText = 'this id is taken';
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function idValidCheck(){
    if(idValid == 0){
        alert("check id!")
        return false;
    }
    else{
        return true;
    }
}