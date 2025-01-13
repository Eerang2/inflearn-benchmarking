document.addEventListener("DOMContentLoaded", () => {
    const banner = document.getElementById("banner");
    const title = document.getElementById("title");
    const mainCategory = document.getElementById("mainCategory");
    const subTagsContainer = document.getElementById("subTagsContainer");
    const addTagButton = document.getElementById("addTagButton");

    // 대분류 선택 시 소분류 태그 초기화
    mainCategory.addEventListener("change", () => {
        const selectedMainCategory = mainCategory.value;

        // 대분류가 선택되지 않았을 경우 처리
        if (!selectedMainCategory) {
            subTagsContainer.innerHTML = "<p>대분류를 선택하세요</p>";
            addTagButton.classList.add("hidden");
            return;
        }

        // API 호출로 소분류 데이터 가져오기
        $.ajax({
            url: `/api/subcategories/` + selectedMainCategory,
            type: "GET",
            success: (data) => {
                populateSubTags(data);
            },
            error: (xhr, status, error) => {
                console.error("Error fetching subcategories:", error);
                subTagsContainer.innerHTML = "<p>소분류 데이터를 불러올 수 없습니다.</p>";
                addTagButton.classList.add("hidden");
            },
        });
    });

    // 소분류 태그 렌더링 함수
    function populateSubTags(subCategories) {
        subTagsContainer.innerHTML = ""; // 기존 태그 초기화
        addTagButton.classList.add("hidden"); // + 버튼 숨김

        if (!subCategories || subCategories.length === 0) {
            const message = document.createElement("p");
            message.textContent = "소분류 데이터가 없습니다.";
            subTagsContainer.appendChild(message);
            return;
        }

        // 소분류 태그 생성
        subCategories.forEach(sub => {
            const tagDiv = document.createElement("div");
            tagDiv.classList.add("tag-item");
            tagDiv.textContent = sub.displayName || sub.name; // API에서 전달받은 태그 이름 사용

            // 태그 선택/해제
            tagDiv.addEventListener("click", () => {
                tagDiv.classList.toggle("selected");
            });

            subTagsContainer.appendChild(tagDiv);
        });

        // + 버튼 표시
        addTagButton.classList.remove("hidden");
    }

    // + 버튼 클릭 시 새 태그 추가
    addTagButton.addEventListener("click", () => {
        const newTag = prompt("새로운 소분류 태그를 입력하세요:");
        if (newTag) {
            const tagDiv = document.createElement("div");
            tagDiv.classList.add("tag-item");
            tagDiv.textContent = newTag;

            // 태그 선택/해제
            tagDiv.addEventListener("click", () => {
                tagDiv.classList.toggle("selected");
            });

            subTagsContainer.appendChild(tagDiv);
        }
    });
    document.getElementById("next").addEventListener("click", () => {
        console.log(banner.files[0])
        const postData = {
            title: title.value,
            banner: banner.files[0],
            mainCategory: mainCategory.value,
            subTags: []
        };

        // 선택된 소분류 태그 가져오기
        const selectedTags = document.querySelectorAll(".tag-item.selected");
        selectedTags.forEach(tag => {
            postData.subTags.push(tag.textContent.trim());
        });

        // AJAX 요청
        $.ajax({
            url: '/api/create/banner',
            type: 'POST',
            data: JSON.stringify(postData),
            contentType: 'application/json',
            success: (response) => {
                alert("배너 및 제목이 저장되었습니다.");
                window.location.href = "/lecture/create/introduction"; // 다음 단계로 이동
            },
            error: (xhr, status, error) => {
                console.error("Error saving banner:", error);
                alert("배너 저장 중 오류가 발생했습니다.");
            }
        });
    });
});
