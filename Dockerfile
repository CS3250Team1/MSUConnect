# Retrieve Docker supported NodeJS image, version 8
FROM node:8

# Create app directory
WORKDIR /app

# Copy node app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package*.json /app

# Install app dependencies
RUN npm install

# Bundle app source
COPY . /app

# Run command "npm start"
CMD [ "npm", "start" ]

# Expose port 8080 in container
EXPOSE 8080
