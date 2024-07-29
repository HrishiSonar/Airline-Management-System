const exp = require('express');
const config = require('config');
const cors = require('cors');
const app = exp();
    const port = config.get("port");
const flightDtlsApp = require("./routes/flightDtls");


app.use(cors());
app.use("/flightDtls", flightDtlsApp);

app.listen(port, ()=>{
    console.log(`Started Listening to port ${port}`);
})