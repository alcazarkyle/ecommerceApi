const API_URL = 'http://localhost:8080/api/products';

async function fetchProducts() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) {
            throw new Error('HTTP Error! Katayuan: ${response.status}');
        }
        const products = await response.json();
        displayProducts(products);
    } catch (error) {
        console.error('Hindi makuha ang mga produkto:', error.message);
        showErrorState();
    }
}

function displayProducts(products) {
    const productContainer = document.querySelector('main');
    if (products.length === 0) {
        productContainer.innerHTML = '<p class="empty">Walang makitang produkto sa ngayon.</p>';
        return;
    }
    productContainer.innerHTML = products.map(product => `
        <div class="product-card">
            <img src="${product.imageUrl || 'placeholder.jpg'}" alt="${product.name}" class="product-image">
            <h3 class="product-name">${product.name}</h3>
            <p class="product-description">${product.description}</p>
            <p class="product-price">₱${product.price.toFixed(2)}</p>
            <p class="product-category">Kategorya: ${product.category.name}</p>
            <p class="product-stock">Natitira: ${product.stockQuantity}</p>
            <button class="add-to-cart">Idagdag sa Cart</button>
        </div>
    `).join('');
}

function showErrorState() {
    const productContainer = document.querySelector('main');
    productContainer.innerHTML = `
        <p class="error">Paumanhin, hindi maikonekta sa server. Subukan muli mamaya.</p>
    `;
}

document.addEventListener('DOMContentLoaded', fetchProducts);