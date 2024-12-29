document.addEventListener("DOMContentLoaded", () => {
    const sidebarLinks = document.querySelectorAll(".sidebar ul li a");
    const sections = document.querySelectorAll(".content .section");

    // 사이드바의 각 링크에 클릭 이벤트를 추가
    sidebarLinks.forEach(link => {
        link.addEventListener("click", (event) => {
            event.preventDefault(); // 기본 동작(페이지 이동)을 막음

            // 선택된 링크를 강조 표시
            sidebarLinks.forEach(link => link.classList.remove("active")); // 모든 링크에서 active 클래스 제거
            link.classList.add("active"); // 현재 클릭된 링크에 active 클래스 추가

            // 클릭된 링크에 연결된 섹션 표시
            const targetId = link.getAttribute("href").substring(1); // 링크의 href 속성에서 섹션 ID 추출
            sections.forEach(section => {
                if (section.id === targetId) {
                    section.style.display = "block"; // 해당 ID의 섹션을 표시
                } else {
                    section.style.display = "none"; // 나머지 섹션은 숨김
                }
            });
        });
    });

    // 첫 번째 섹션만 기본적으로 표시하고 나머지는 숨김
    sections.forEach((section, index) => {
        section.style.display = index === 0 ? "block" : "none";
    });
});
