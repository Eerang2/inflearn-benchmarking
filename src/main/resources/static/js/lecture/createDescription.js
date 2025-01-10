document.getElementById('next').addEventListener('click', () => {
    const description = document.getElementById('description').value;
    if (!description.trim()) {
        alert('강의 소개를 입력해주세요.');
        return;
    }
    alert('다음 단계로 이동합니다.');
    // 다음 단계로 이동하는 코드 추가
    window.location.href = "step3.html"; // Example
});
