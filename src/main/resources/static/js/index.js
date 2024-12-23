// 카테고리 클릭 시 콘솔에 출력되는 간단한 JS 예시
document.querySelectorAll('.category-item').forEach(item => {
    item.addEventListener('click', function() {
        alert(`"${this.textContent}" 카테고리를 선택했습니다.`);
    });
});

// 배너 버튼 클릭 시 페이지 이동 예시 (현재는 예시로 위치 변경)
document.querySelector('.banner button').addEventListener('click', function() {
    window.location.href = '/';
});
