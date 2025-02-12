
document.getElementById('next').addEventListener('click', () => {
    const description = document.getElementById('description').value;
    if (!description.trim()) {
        alert('강의 소개를 입력해주세요.');
        return;
    }
    const postData = {
        content: description,
    };
    $.ajax({
        url: '/api/create/description',
        type: 'POST',
        data: JSON.stringify(postData),
        contentType: 'application/json',
        success: (response) => {
            alert("강의 소개가 저장되었습니다.");
            window.location.href = "/create/videoupload"; // 다음 단계로 이동
        },
        error: (xhr, status, error) => {
            console.error("Error saving banner:", error);
            alert("소개글 저장 중 오류가 발생했습니다.");
        }
    });
});
