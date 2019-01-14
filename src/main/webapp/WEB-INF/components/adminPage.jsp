
<section >
    <h3>Categories</h3>
    <ul id="category-list"></ul>
    <input id="categoryName" />
    <input id="addCategoryButton" type="button" value="Add Category" />
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

        categories.forEach((cat) => {
            listItem = document.createElement('li')
            listItem.innerText = cat.categori
            categoryList.appendChild(listItem)
        })
    }

    document.getElementById('addCategoryButton').addEventListener('click', (event) => {
        addNewCategory()
    })

    refreshCategoryList()

</script>
