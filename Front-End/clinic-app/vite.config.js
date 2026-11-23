import react from '@vitejs/plugin-react'
import { defineConfig } from 'vite'

// https://vite.dev/config/

// vite.config.js
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: { '/api': 'http://localhost:8080' }
  }
})