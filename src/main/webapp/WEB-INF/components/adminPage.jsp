
<section>
    <h3>Categories</h3>
    <form class="form-inline">
        <input id="categoryName" class="form-control mb-2" />
        <input id="addCategoryButton" class="btn btn-primary" type="button" value="Add Category" />
    </form>

    <div id="category-list"></div>
</section>

<script type="text/javascript">
    const csrfToken = document.getElementsByName('_csrf')[0].getAttribute('content')

    const addNewCategory = () => {
        let categoryName = document.getElementById('categoryName').value
        fetch(`/categories/\${categoryName}`, {
            method: 'post',
            headers: {
                'X-CSRF-TOKEN': csrfToken
            }
        }).then(refreshCategoryList)
    }

    const removeCategory = (categoryName) => {
        console.log(`Removing category \${categoryName}`)

        fetch(`/categories/\${categoryName}`, {
            method: 'delete',
            headers: {
                'X-CSRF-TOKEN': csrfToken
            }
        }).then(refreshCategoryList)
    }

    const refreshCategoryList = () => {
        fetch('/categories')
          .then((response) => {
            return response.json()
          })
          .then((categories) => populateCategoriesList(categories))
    }

    const populateCategoriesList = (categories) => {
        let categoryList = document.getElementById('category-list')

        // remove all categories first
        while (categoryList.firstChild) {
            categoryList.removeChild(categoryList.firstChild)
        }

        categories.forEach((category) => {
            categoryList.appendChild(createCategoryRow(category.id, category.categori, category.categoriImg))
        })
    }

    const createCategoryRow = (id, categoryName, imgUrl) => {
        categoryRowTmplt = document.querySelector("#category-row")
        categoryRow = document.importNode(categoryRowTmplt.content, true)
        categoryRow.querySelector(".category__name").innerText = categoryName
        categoryRow.querySelector(".category__id").innerText = id
        if (imgUrl) {
            categoryRow.querySelector('.category__thumbnail').src = imgUrl
            categoryRow.querySelector('.category__img-upload').style.display = 'none'
            categoryRow.querySelector('.category__thumbnail').style.display = 'inline'
        } else {
            categoryRow.querySelector('.category__thumbnail').style.display = 'none'
            categoryRow.querySelector('.category__img-upload').style.display = 'inline'
        }

        categoryRow.querySelector('.category__delete').addEventListener('click', () => {
            removeCategory(categoryName)
        })

        categoryRow.querySelector('.category__img-upload')
            .addEventListener("change", function(evt) {
                const selectedFile = evt.target.files[0]
                console.log(selectedFile.name)

                const categoryId = evt.target.parentNode.querySelector('.category__id').innerText
                const formData  = new FormData()
                formData.append('file', selectedFile)

                fetch(`/CategoryImgs/\${categoryId}`, {
                    method: 'post',
                    headers: {
                        'X-CSRF-TOKEN': csrfToken,
                    },
                    body: formData
                }).then(refreshCategoryList)
            }, false)

        return categoryRow
    }

    document.getElementById('addCategoryButton').addEventListener('click', (event) => {
        addNewCategory()
    })


    window.onload = () => refreshCategoryList()
</script>

<template id="category-row">
    <div class="category">
        <!--TODO hide ID -->
        <div class="category__id d-none">ID</div>
        <input class="category__delete btn btn-danger" type="button" value="Delete" />
        <span class="category__name">Category Name</span>
        <input class="category__img-upload" type="file" value="Upload img" /> <!--TODO hidden if not null -->
        <img class="category__thumbnail" src="" />
    </div>
</template>
